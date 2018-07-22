package pack;

public class Summary {

	String id;
	String state;
	String numberOfDistrict;
	String numberOfBlocks;
	String numberOfGP;
	String routeKm;
	String costOfAward;
	String numberOfGpInWO;
	String incrementalLength;
	
	
	
	public Summary(String id, String state, String numberOfDistrict, String numberOfBlocks, String numberOfGP,
			String routeKm, String costOfAward, String numberOfGpInWO, String incrementalLength) {
		super();
		this.id = id;
		this.state = state;
		this.numberOfDistrict = numberOfDistrict;
		this.numberOfBlocks = numberOfBlocks;
		this.numberOfGP = numberOfGP;
		this.routeKm = routeKm;
		this.costOfAward = costOfAward;
		this.numberOfGpInWO = numberOfGpInWO;
		this.incrementalLength = incrementalLength;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getNumberOfDistrict() {
		return numberOfDistrict;
	}



	public void setNumberOfDistrict(String numberOfDistrict) {
		this.numberOfDistrict = numberOfDistrict;
	}



	public String getNumberOfBlocks() {
		return numberOfBlocks;
	}



	public void setNumberOfBlocks(String numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}



	public String getNumberOfGP() {
		return numberOfGP;
	}



	public void setNumberOfGP(String numberOfGP) {
		this.numberOfGP = numberOfGP;
	}



	public String getRouteKm() {
		return routeKm;
	}



	public void setRouteKm(String routeKm) {
		this.routeKm = routeKm;
	}



	public String getCostOfAward() {
		return costOfAward;
	}



	public void setCostOfAward(String costOfAward) {
		this.costOfAward = costOfAward;
	}



	public String getNumberOfGpInWO() {
		return numberOfGpInWO;
	}



	public void setNumberOfGpInWO(String numberOfGpInWO) {
		this.numberOfGpInWO = numberOfGpInWO;
	}



	public String getIncrementalLength() {
		return incrementalLength;
	}



	public void setIncrementalLength(String incrementalLength) {
		this.incrementalLength = incrementalLength;
	}



	public Summary() {
		super();
	}
	
}
