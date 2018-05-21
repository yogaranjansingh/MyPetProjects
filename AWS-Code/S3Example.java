import java.io.File;
import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Example {
	
	private static final String SUFFIX = "/";	
	
	public static void main(String[] args) throws IOException {
		
		
		//s3client.createBucket(bucketName);
		//Student s1 =new Student(211,"ravi");
		//File file = new File("s3select.json");
		//FileOutputStream fout=new FileOutputStream(file);  
		//ObjectOutputStream out=new ObjectOutputStream(fout);  
		//out.writeObject(s1);  
		//out.flush();
		
		upload();
		
	}
	
	@Benchmark
	static void upload()
	{
		AWSCredentials credentials = new BasicAWSCredentials("AKIAI756VXTRTRPHWXRA", "+8g/o4KLel7PZuYmg9NXFkvO3ks/Emk9YCDaPSml");
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();
		File file = new File("sample.csv");
		String bucketName = "examplebucketgeocode";
		s3client.createBucket(bucketName);
		PutObjectRequest request = new PutObjectRequest(bucketName, "sample.csv", new File("sample.csv"));
		s3client.putObject(request);
	}
	
	static void select()
	{
		
	}
}
