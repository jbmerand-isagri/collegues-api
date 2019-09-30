package dev.collegues_api.controller.dto;

public class ColleguePhotosDto {

    private String photoUrl;
    private String matricule;

    public ColleguePhotosDto() {
    }

    public ColleguePhotosDto(String photoUrl, String matricule) {
        this.photoUrl = photoUrl;
        this.matricule = matricule;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
