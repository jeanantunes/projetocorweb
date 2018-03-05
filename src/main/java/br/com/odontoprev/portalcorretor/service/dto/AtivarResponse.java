package br.com.odontoprev.portalcorretor.service.dto;

public class AtivarResponse {

    private String id;
    private String message;

    public AtivarResponse() {
    }

    public AtivarResponse(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
