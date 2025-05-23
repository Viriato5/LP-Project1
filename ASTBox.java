class ASTBox implements ASTNode  {
    ASTNode arg;

    ASTBox(ASTNode arg0) {
        arg = arg0;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VPointer(arg.eval(e));               
    }

}
