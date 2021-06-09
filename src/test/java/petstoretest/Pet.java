package petstoretest;

public class Pet {

	public class Category {
		 private String id;
		 private String name;
		 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public Category(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		 
	}
	
	public class Tag {
		private String id;
		private String name;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public Tag(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}
	
	private String id;
	private Category category;
	private String name;
	private String[] photoUrls;
	private Tag[] tags;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Tag[] getTags() {
		return tags;
	}
	public void setTags(Tag[] tags) {
		this.tags = tags;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Pet(String name) {
		super();
		
		Category category = new Category("0", "category0");
		Tag tag = new Tag("0", "tag0");
		
		this.id = "0";
		this.category = category;
		this.name = name;
		this.photoUrls = new String[] {"http://www.google.es"};
		this.tags = new Tag[] {tag};
		this.status = "available";
		
	}
	
	
}
