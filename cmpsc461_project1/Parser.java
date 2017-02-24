// CMPSC 461 Project 1
// Galip
class Parser {

    Lexer lexer;
    Token token;
    
    public Parser(String s){
        lexer = new Lexer(s + "$");
        token = lexer.nextToken();
    }

    public void run () {
        query();
    }

    public void query () {  //we start with query as this the beigning of parser
        System.out.println("<Query>");
        if(!(token.getTokenValue().equals("SELECT"))){  //make sure the first value is select or else wont work
          System.err.println("Syntax error, you have to have SELECT");
          System.exit(1);
        }
        System.out.println("\t<Keyword>"+token.getTokenValue()+"</Keyword>");
        token= lexer.nextToken(); //must move to the next value
        idList(); //call IDList for parsing
        if(!(token.getTokenValue().equals("FROM"))){  //again make sure we have from or not
          System.err.println("Syntax error, you have to have FROM");
          System.exit(1);
        }
        System.out.println("\t<Keyword>"+token.getTokenValue()+"</Keyword>");
        token= lexer.nextToken();
        idList();  //again call IDlist
        
        while(token.getTokenType() == Token.TokenType.KEYWORD){ //this is optional but neccasary if there are other keywords
          System.out.println("\t<Keyword>"+token.getTokenValue()+"</Keyword>");
          condList();
        }
        match(Token.TokenType.EOI);
        System.out.println("</Query>");
    }
    
    public void idList () {  // this IDlist
      System.out.println("\t<IdList>");
      String val4 = match(Token.TokenType.ID);
      System.out.println("\t\t<Id>"+val4+"</Id>");
      while(token.getTokenType() == Token.TokenType.COMMA){ //again this can be optional depending if there is comma
        System.out.println("\t\t<Comma>,</Comma>");
        token= lexer.nextToken();  //must move to the next one
        String val2 = match(Token.TokenType.ID);
        System.out.println("\t\t<Id>"+val2+"</Id>");
      }
      System.out.println("\t</IdList>");
    }
    
    public void condList(){ // this is CondList
      System.out.println("\t<CondList>");
      cond();  //condition is another part of parsing so must call it
      while(token.getTokenType() == Token.TokenType.KEYWORD){
        System.out.println("\t\t<Keyword>AND</Keyword>");
        token= lexer.nextToken();
        cond();  //call cond for parsing
      }
      System.out.println("\t</CondList>");
    }
    
    public void cond() {
      System.out.println("\t\t<Cond>"); ///this is for cond make sure of id's for operator =
      token= lexer.nextToken();
      String val3 =match(Token.TokenType.ID);
      System.out.println("\t\t\t<Id>"+val3+"</Id>");
      match(Token.TokenType.OPERATOR);
      System.out.println("\t\t\t<Operator>=</Operator>");
      term(); //calling term for the last part of parsing
      System.out.println("\t\t</Cond>");
    }
    
    public void term () {
      System.out.println("\t\t\t<Term>");  //it has floats int id the final part of the parsing
        if (token.getTokenType() == Token.TokenType.ID) {
            System.out.println("\t\t\t\t<Identifier>" + token.getTokenValue()
                               + "</Identifier>");
        } else if (token.getTokenType() == Token.TokenType.FLOAT) {
            System.out.println("\t\t\t\t<Float>" + token.getTokenValue()
                               + "</Float>");
            
        } else if (token.getTokenType() == Token.TokenType.INT) {
            System.out.println("\t\t\t\t<Int>" + token.getTokenValue()
                               + "</Int>");
        }else {
            System.err.println("Syntax error: expecting an ID, an int, or a float"
                               + "; saw:"
                               + Token.typeToString(token.getTokenType()));
            System.exit(1);
        }
        System.out.println("\t\t\t</Term>");                 
        token = lexer.nextToken();
    }

    private String match (Token.TokenType tp) { //checker for matching correct info
        String value = token.getTokenValue();
   //     System.out.println(value);
   //     System.out.println(tp);
        if (token.getTokenType() == tp)
            token = lexer.nextToken();
        else error(tp);
        return value;
    }

    private void error (Token.TokenType tp) {  //error message
        System.err.println("Syntax error: expecting: " + Token.typeToString(tp)
                           + "; saw: "
                           + Token.typeToString(token.getTokenType()));
        System.exit(1);
    }

}
