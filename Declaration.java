// import java.util.logging.Logger;

public class Declaration {
    private final String declarationId;
    private String content;
    private DeclarationStatus status;
    
    public enum DeclarationStatus { PENDING, APPROVED, REJECTED }

    public Declaration(String declarationId, String content) {
        this.declarationId = declarationId;
        this.content = content;
        this.status = DeclarationStatus.PENDING;
    }

    public String getDeclarationId() {
        return declarationId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public DeclarationStatus getStatus() {
        return status;
    }

    public void setStatus(DeclarationStatus status) {
        this.status = status;
    }

    public void approve() {
        this.status = DeclarationStatus.APPROVED;
        System.out.println("Declaration " + declarationId + " approved.");
    }

    public void reject() {
        this.status = DeclarationStatus.REJECTED;
        System.out.println("Declaration " + declarationId + " rejected.");
    }
    
    @Override
    public String toString() {
        return "Declaration ID: " + declarationId + ", Content: " + content + ", Status: " + status;
    }
}
