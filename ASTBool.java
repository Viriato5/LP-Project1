class ASTBool implements ASTNode  {
    boolean val;

    ASTBool(boolean v) {
        val = v;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VBool(val);                
    }

}
