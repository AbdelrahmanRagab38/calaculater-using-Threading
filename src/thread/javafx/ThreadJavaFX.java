
package thread.javafx;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class ThreadJavaFX extends Application {

    
    public static void main(String[] args) {
      launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    
    FlowPane pane = new FlowPane();
    pane.setHgap(2);
    pane.setVgap(5);
    pane.setAlignment(Pos.CENTER);
    TextField tfNumber1 = new TextField();
    TextField tfNumber2 = new TextField();
    TextField tfResult = new TextField();
    tfResult.setEditable(false);
    tfResult.setPrefColumnCount(15);

    
    pane.getChildren().addAll(new Label("Number 1: "), tfNumber1, new Label("Number 2: "), tfNumber2, new Label("Result: "), tfResult);
      
    Button btAdd = new Button("Add");
    Button btSubtract = new Button("Subtract");
    
    pane.getChildren().addAll(btAdd, btSubtract);

   Scene scene = new Scene(pane, 250, 200);
   primaryStage.setTitle("Calculator");        
   primaryStage.setScene(scene); 
   primaryStage.show(); 

   
   btAdd.setOnAction(e -> {
      
      // Thread t1 = new Thread(new Add(Integer.parseInt(tfNumber1.getText()),
       //        Integer.parseInt(tfNumber2.getText()) ));
       
        Add t1=new Add(Integer.parseInt(tfNumber1.getText()),
              Integer.parseInt(tfNumber2.getText())  
      );
       t1.start();
                  try {
			t1.join();  //to make th thread finish all his functionality to avoid wrong answer
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
      
           tfResult.setText(String.valueOf(t1.res));
    });
   
   
   

    btSubtract.setOnAction(e -> {
        
     sub t2=new sub(Integer.parseInt(tfNumber1.getText()),
               Integer.parseInt(tfNumber2.getText()));
t2.start();
			try {
			t2.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
           tfResult.setText(String.valueOf(t2.res));

    });
               

    
    }

  

 
    
    

   public  class Add extends Thread {
       int n1,n2,res;
       public Add(){};
public Add(int num1,int num2){
this.n1=num1; this.n2=num2;
}
        @Override
        public void run() {
            
             this.res=n1+n2;
//tfResult.setText(n1+n2);
    
        }
   }
   
   
    public  class sub  extends Thread{
       int n1,n2,res;
              public sub(){};

public sub(int num1,int num2){
n1=num1; n2=num2;
}
        @Override
        public void run() {
                 res=n1-n2;
        }
   }
    
}
