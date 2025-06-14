public class ASTTRef implements ASTType {

    private ASTType type;

    public ASTTRef(ASTType type) {
        this.type = type;
    }
    
    public ASTType getType() {
        return type;
    }

    public String toStr() {
        return "ref<"+type.toStr()+">";
    }

    public boolean equals(Object o) {
        if (o instanceof ASTTRef) {
            ASTTRef other = (ASTTRef) o;
            return this.type.equals(other.type);
        }
        return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            ASTType otherType = otherId.get(env);
            return this.isSubTypeOf(otherType, env);
        }
        if (other instanceof ASTTRef) {
            ASTTRef otherRef = (ASTTRef) other;
            return this.type.isSubTypeOf(otherRef.getType(), env);
        }
        return false;
    }

}