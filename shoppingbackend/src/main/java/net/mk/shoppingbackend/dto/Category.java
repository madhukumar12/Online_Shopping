package net.mk.shoppingbackend.dto;

public class Category
{
  private int id;
  private String name;
  private String descripton;
  private String imageURL;
  private boolean active = true;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDescripton()
  {
    return this.descripton;
  }
  
  public void setDescripton(String descripton)
  {
    this.descripton = descripton;
  }
  
  public String getImageURL()
  {
    return this.imageURL;
  }
  
  public void setImageURL(String imageURL)
  {
    this.imageURL = imageURL;
  }
  
  public boolean isActive()
  {
    return this.active;
  }
  
  public void setActive(boolean active)
  {
    this.active = active;
  }
}
