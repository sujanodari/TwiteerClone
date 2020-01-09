package com.sujan.twiteerclone.model;

public class CreateUser {
    String username,password,phone,email,profileImage,bio,interest;

    public CreateUser(String username, String password, String phone, String email, String profileImage, String bio, String interest) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.profileImage = profileImage;
        this.bio = bio;
        this.interest = interest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
