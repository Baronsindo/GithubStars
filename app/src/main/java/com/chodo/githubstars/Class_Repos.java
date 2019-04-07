package com.chodo.githubstars;

public class Class_Repos {

    private String Name,Desc,Stars,Owner,Image_Url;

    public Class_Repos(String name, String desc, String stars, String owner, String image_Url) {
        Name = name;
        Desc = desc;
        Stars = stars;
        Owner = owner;
        Image_Url = image_Url;
    }

    @Override
    public String toString() {
        return "Class_Repos{" +
                "Name='" + Name + '\'' +
                ", Desc='" + Desc + '\'' +
                ", Stars='" + Stars + '\'' +
                ", Owner='" + Owner + '\'' +
                ", Image_Url='" + Image_Url + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

    public String getStars() {
        return Stars;
    }

    public String getOwner() {
        return Owner;
    }

    public String getImage_Url() {
        return Image_Url;
    }
}
