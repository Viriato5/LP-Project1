public class ASTTArrow implements ASTType {
    public ASTType dom;
    public ASTType codom;

    public ASTTArrow(ASTType d, ASTType co) {
        dom = d;
        codom = co;

    }

    public ASTType getArgType() {
        return dom;
    }

    public ASTType getReturnType() {
        return codom;
    }

    public String toStr() {
        return "(" + dom.toStr()+")->("+codom.toStr()+")";
    }   


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ASTTArrow)) return false;
        ASTTArrow that = (ASTTArrow) o;
        if (!dom.equals(that.dom)) return false;
        return codom.equals(that.codom);
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            ASTType otherType = otherId.get(env);
            return this.isSubTypeOf(otherType, env);
        }
        if (other instanceof ASTTArrow) {
            ASTTArrow otherA = (ASTTArrow) other;
            return otherA.dom.isSubTypeOf(this.dom, env) && this.codom.isSubTypeOf(otherA.codom, env);
        }
        return false;
    }
}

