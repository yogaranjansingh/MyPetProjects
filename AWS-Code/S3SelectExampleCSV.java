	import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CSVInput;
import com.amazonaws.services.s3.model.CSVOutput;
import com.amazonaws.services.s3.model.CompressionType;
import com.amazonaws.services.s3.model.ExpressionType;
import com.amazonaws.services.s3.model.InputSerialization;
import com.amazonaws.services.s3.model.JSONInput;
import com.amazonaws.services.s3.model.JSONOutput;
import com.amazonaws.services.s3.model.JSONType;
import com.amazonaws.services.s3.model.OutputSerialization;
import com.amazonaws.services.s3.model.SelectObjectContentEvent;
import com.amazonaws.services.s3.model.SelectObjectContentEventVisitor;
import com.amazonaws.services.s3.model.SelectObjectContentRequest;
import com.amazonaws.services.s3.model.SelectObjectContentResult;

public class S3SelectExampleCSV {

	private static final String BUCKET_NAME = "examplebucketgeocode";
	private static final String CSV_OBJECT_KEY = "sample.csv";
	private static final String S3_SELECT_RESULTS_PATH = "sampleOutput.csv";
	//private static final String QUERY = "select s._1 from S3Object s";
	//private static final String QUERY = "Select s.person p from S3Object s where p.name='yoga'";
	private static final String QUERY = "Select * from S3Object s where _6 = '123' ";
	

	public static void main(String[] args) throws Exception {
		
		AWSCredentials credentials = new BasicAWSCredentials("AKIAI756VXTRTRPHWXRA", "+8g/o4KLel7PZuYmg9NXFkvO3ks/Emk9YCDaPSml");

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
	            .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();
	            Regions bucketRegion = Regions.US_EAST_1;
	            
		SelectObjectContentRequest request = generateBaseCSVRequest(BUCKET_NAME, CSV_OBJECT_KEY, QUERY);
		final AtomicBoolean isResultComplete = new AtomicBoolean(false);

		try (OutputStream fileOutputStream = new FileOutputStream(new File (S3_SELECT_RESULTS_PATH));
	             SelectObjectContentResult result = s3client.selectObjectContent(request)) {
	            InputStream resultInputStream = result.getPayload().getRecordsInputStream(
	                    new SelectObjectContentEventVisitor() {
	                        @Override
	                        public void visit(SelectObjectContentEvent.StatsEvent event)
	                        {
	                            System.out.println(
	                                    "Received Stats, Bytes Scanned: " + event.getDetails().getBytesScanned()
	                                            +  " Bytes Processed: " + event.getDetails().getBytesProcessed());
	                        }

	                        /*
	                         * An End Event informs that the request has finished successfully.
	                         */
	                        @Override
	                        public void visit(SelectObjectContentEvent.EndEvent event)
	                        {
	                            isResultComplete.set(true);
	                            System.out.println("Received End Event. Result is complete.");
	                        }
	                    }
	            );

	            copy(resultInputStream, fileOutputStream);
	        }

	        /*
	         * The End Event indicates all matching records have been transmitted.
	         * If the End Event is not received, the results may be incomplete.
	         */
	        if (!isResultComplete.get()) {
	            throw new Exception("S3 Select request was incomplete as End Event was not received.");
	        }
	}

	private static void copy(InputStream in, OutputStream out) throws IOException {
		final byte[] b = new byte[8192];
	    for (int r; (r = in.read(b)) != -1;) {
	        out.write(b, 0, r);
	    }
	}
	
	static void printInputStream(InputStream in)
	{
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(sb.toString());
	}

	 private static SelectObjectContentRequest generateBaseCSVRequest(String bucket, String key, String query) {
	        SelectObjectContentRequest request = new SelectObjectContentRequest();
	        request.setBucketName(bucket);
	        request.setKey(key);
	        request.setExpression(query);
	        request.setExpressionType(ExpressionType.SQL);

	        InputSerialization inputSerialization = new InputSerialization();
	        CSVInput input = new CSVInput();
	        //input.setType(JSONType.DOCUMENT);
	        inputSerialization.setCsv(input);
	        inputSerialization.setCompressionType(CompressionType.NONE);
	        request.setInputSerialization(inputSerialization);

	        OutputSerialization outputSerialization = new OutputSerialization();
	       // JSONOutput output = new JSONOutput();
	        
	        outputSerialization.setCsv(new CSVOutput());
	        request.setOutputSerialization(outputSerialization);

	        return request;
	    }

}
