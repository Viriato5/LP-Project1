public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    
    public String toStr() {
        return id;
    }

    public ASTType get(Environment<ASTType> e) throws TypeCheckerError {
        try {
        ASTType type = e.find(id);
        if (type == null) {
            throw new TypeCheckerError("Type not found: " + id);
        }

        return type;
        } catch (InterpreterError err) {
            throw new TypeCheckerError(err.getMessage());
        }
    }

    public boolean equals(Object o) {
        if (o instanceof ASTTId) {
            ASTTId other = (ASTTId) o;
            return this.id.equals(other.id);
        }
        return false;
    }

    @Override
    public boolean isSubTypeOf(ASTType other, Environment<ASTType> env) throws TypeCheckerError {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            if (this.equals(otherId)) {
                return true;
            }

            ASTType thisType = this.get(env);
            ASTType otherType = otherId.get(env);
            return thisType.isSubTypeOf(otherType, env);
        }
        else {
            ASTType thisType = this.get(env);
            return thisType.isSubTypeOf(other, env);
        }
    }

}	
