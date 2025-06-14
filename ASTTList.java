public class ASTTList implements ASTType {
    private ASTType elt;

    public ASTTList(ASTType eltt)
    {
        elt = eltt;
    }

    public ASTType getElementType() {
        return elt;
    }
    
    public String toStr() {
        return "list<"+elt.toStr()+">";
    }
    
    public boolean equals(Object o) {
        if (o instanceof ASTTList) {
            ASTTList other = (ASTTList) o;
            if (this.elt instanceof ASTTUnit && !(other.getElementType() instanceof ASTTUnit)) {
                return true;
            }
            return this.elt.equals(other.getElementType());
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
        if (other instanceof ASTTList) {
            ASTTList otherList = (ASTTList) other;
            return otherList.getElementType() instanceof ASTTUnit || this.elt.equals(otherList.getElementType());
        }
        return false;
    }
}
