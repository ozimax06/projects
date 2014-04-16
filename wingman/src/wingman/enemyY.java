

package wingman;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

//enemy Y is a subclass enemy type that
//attacks the plane from the vertical direction(Y axis)
public class enemyY extends Enemy 
{
      //the variables that are declared in the
    //abstract Enemy class can be used in this subclass
    //without declaring them.
    
    int i = 0;//index of the image array
      
      enemyY(int speed, Random gen)
      {
          super(speed, gen);//super always MUST be first ARGUMENT!!
          
          this.img[0] = gm.getSprite("Resources/enemy1_1.png");
          this.show = true;
          sizeX = img[0].getWidth(null);
          sizeY = img[0].getHeight(null);
          this.x = Math.abs(gen.nextInt() % (600 - 30));
          this.y = -100;
      }
      
      public void update(int w, int h) 
        {
            //if the enemy reaches the bottom of the screen
            //its x value will be regenerated
            if(y>=800)
            {    
               show = true;
               i = 0;
               y=-100;
               x = Math.abs(gen.nextInt() % (600 - 30));
            }
            y += speed;
            
	    //detects collision. If it does,
	    //then show will be false.
            //In order to make the collision event happen,
            //show must be true which means the enemy must present
            if(gm1942.m.collision(x, y, sizeX, sizeY)&& show) 
            {
                
                show = false;
                gm1942.gameEvents.setValue("Explosion");
                System.out.println("explosion");
            }
            if(gm1942.m2.collision(x, y, sizeX, sizeY)&& show) 
            {
                
                show = false;
                gm1942.gameEvents.setValue("Explosion2");
                System.out.println("explosion");
            }
            else if(gm1942.f.collision(x, y, sizeX, sizeY)&&show)
            {
                
                 show = false;
                 gm1942.gameEvents.setValue("Enemy destroyed");
                 System.out.println("Enemy Destroyed");
                
            }    
        }//end of update

        public void draw(Graphics g, ImageObserver obs) {
            if (show) {
                g.drawImage(img[i], x, y, obs);
            }
            //draws the explosion images if the enemy plane

            else if(!show)
            {
                if(i<4)
                   g.drawImage(img[i++], x, y, obs);
            }     
        }
    }//end of enemy
    
    

