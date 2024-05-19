import processing.core.PApplet;
import processing.core.PImage;
public class Sketch extends PApplet {
  PImage img;
  float [] snowX = new float[42];
  float [] snowY = new float [42];
  boolean[] blnHideStatus = new boolean[42];
  int snowDiameter = 10;
  
  public void settings() {
    size(400, 400);
  }
  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean wPressed = false;
  boolean aPressed = false;
  boolean sPressed = false;
  boolean dPressed = false;

  int playerLives = 3;
  public void setup() {

    img = loadImage("funnydog.jpg");

    background(0);

    for (int i = 0; i < snowX.length; i++){
      snowX[i] = random (width);
      snowY[i] = random (height);
    }

  }

  float imgX = 100;
  float imgY = 100;

  public void draw() {
    if (playerLives > 0) {
      background(0);
      snow();
      fill(255, 45, 255);
      imgX = constrain(imgX, 0, width - 50); // 50 is the width of the image
      imgY = constrain(imgY, 0, height - 50); // 50 is the height of the image
      image(img, imgX, imgY, 50, 50);

      // Draw player lives
      drawPlayerLives();
  } else {
      // Display white screen when player loses all lives
      background(255);
  }
}
  
  

  public void snow() {

    int intLives = 3;

    fill(255);
    for (int i = 0; i < snowX.length; i++) {
      if (!blnHideStatus[i]) {
      circle(snowX[i], snowY[i], snowDiameter);
      snowY[i] +=1;
      }

      if (collision(imgX + 25, imgY + 25, snowX[i], snowY[i], 25, snowDiameter)) {
        // Decrement player lives if collision occurs
        playerLives--;
        // Reset snowflake position
        snowY[i] = 0;
    }


     if (upPressed) {
      snowY[i] -=0.5;
     }

     if (downPressed) {
      snowY[i] +=2;
     }

     if (wPressed) {
      imgY -= movementSpeed;
  }

  if (sPressed) {
      imgY += movementSpeed;
  }

  if (aPressed) {
      imgX -= movementSpeed;
  }

  if (dPressed) {
      imgX += movementSpeed;
  }


  if (snowY[i] > height) {
    snowY[i] = 0;
  }

  }
 
  }

    

  float movementSpeed = (float) 0.1;
  float snowFallSpeed = (float)1.0;

  public void keyPressed() {
    if (keyCode == UP) {
      upPressed = true;
    }
    else if (keyCode == DOWN) {
      downPressed = true;
    }
    else if (key == 'w') {
      wPressed = true;
    }
    else if (key == 'a') {
      aPressed = true;
    }
    else if (key == 's') {
      sPressed = true;
    }
    else if (key == 'd') {
      dPressed = true;
    }
  }

  
  public void keyReleased () {
    if (keyCode == UP) {
      upPressed = false;
    }
    else if (keyCode == DOWN) {
      downPressed = false;
    }
    else if (key == 'w') {
      wPressed = false;
    }
    else if (key == 'a') {
      aPressed = false;
    }
    else if (key == 's'){
      sPressed = false;
    }
    else if (key == 'd') {
      dPressed = false;
    }
  }
  public void mousePressed() {
    for (int i = 0; i < snowX.length; i++) {
      if (dist(snowX[i], snowY[i], mouseX, mouseY) < snowDiameter) {
        blnHideStatus[i] = true;
      }
    }
  }

  public boolean collision(float x1, float y1, float x2, float y2, float r1, float r2) {
    float dx = x1 - x2;
    float dy = y1 - y2;
    float distance = sqrt(dx * dx + dy * dy);
    return distance < r1 + r2;
  
  }
  public void drawPlayerLives() {
    
    for (int i = 0; i < playerLives; i++) {
        float x = width - 50 + i * 20;
        float y = 20;
        rect(x, y, 10, 10);
    }
  
  }
}

