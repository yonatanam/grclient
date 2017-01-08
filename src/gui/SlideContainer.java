package gui;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

class SlideContainer extends JLayeredPane {
   private static  int PREF_W ;
   private static  int PREF_H ;
   private static final int SLIDE_DELAY = 1;
   protected static final int DELTA_X = 2;
   private Component menu;
   private int direction=1;
  

   public SlideContainer(Component menu,Rectangle r) {
      setLayout(null);
      this.menu=menu;      
      this.menu.setSize(r.width,r.height);      
      PREF_W=r.width;
      PREF_H=r.height;
      this.menu.setLocation(-r.width, this.menu.getY());      
      menu.setVisible(false);
      super.add(this.menu);
      slideFromRight(this.menu,this);
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   public  int getDirection() {
	      return direction;
	   }
   public  void setDirection(int d) {
	      direction=d;
	   }
 
   
   public Component Switch() {
   
	      if(direction==1)direction=0;
	      else direction=1;
	      putLayer((JComponent) menu, JLayeredPane.DRAG_LAYER);
	      menu.setVisible(true);
	      if(this.direction==0)   slideFromLeft(menu,this);	    
	      else slideFromRight(menu,this);
		  return menu;

   }
   public   void slideFromRight(final Component comp,final SlideContainer c)
   {
	  
      new Timer(SLIDE_DELAY, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent aEvt) {
        	 if(c.getDirection()==0)  ((Timer) aEvt.getSource()).stop();
            int x = comp.getX();            
            //System.out.println("x= "+x);
            if (x+comp.getWidth() <= 0) {
               ((Timer) aEvt.getSource()).stop();
            } else {
               x -= DELTA_X;
               comp.setLocation(x, 0);
            }
            repaint();
         }
      }).start();
   }
   public  void slideFromLeft(final Component comp,final SlideContainer c)
   {
      new Timer(SLIDE_DELAY, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent aEvt) {
        	 if(c.getDirection()==1)  ((Timer) aEvt.getSource()).stop();
            int x = comp.getX();
            //System.out.println(x);
            if (x >= 0) {
               comp.setLocation(0, 0);
               putLayer((JComponent) comp, JLayeredPane.DEFAULT_LAYER);
               
               ((Timer) aEvt.getSource()).stop();
            } else {
               x += DELTA_X;
               comp.setLocation(x, 0);
            }
            repaint();
         }
      }).start();
   }
}