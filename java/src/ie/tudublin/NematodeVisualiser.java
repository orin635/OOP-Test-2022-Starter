package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{

	ArrayList<Nematode> nematodes = new ArrayList<Nematode>();
	int position;


	public void keyPressed()
	{		
		if (keyCode == LEFT)
		{
			position = position - 1;
			if(position < 0){
				position = (nematodes.size() - 1);
			}
		}		
		if (keyCode == RIGHT)
		{
			position = position + 1;
			if(position > (nematodes.size() - 1)){
				position = 0;
			}
		}
	}


	public void settings()
	{
		size(800, 800);
	}

	public void setup() 
	{
		colorMode(HSB);
		background(0);
		strokeWeight(5);
		noFill();
		smooth();	
		loadNematodes();	
		printNematodes();	
		
		position = 0;
	}
	

	void printNematodes(){
		for(Nematode n:nematodes){
			System.out.println(n);	
		}
		System.out.println(nematodes.size());
	}

	public void loadNematodes()
	{
		Table table = loadTable("nematodes.csv","header");
		for(TableRow r:table.rows())
		{
			Nematode n = new Nematode(r);
			nematodes.add(n);
		}
	}


	public void drawNematodes(){
		Nematode nem = nematodes.get(position);
		float radius = 50;
		String name = nem.getName();
		int length = nem.getLength();
		boolean limbs = nem.isLimbs();
		String gender = nem.getGender();
		boolean eyes = nem.isEyes();

		float color = map(position, 0, (nematodes.size()), 0, 255);

		stroke(color, 255, 255);
	
		float startY = height/2 - ((length/2) * radius);

		noFill();


		for(int i =0;i < length; i++){
			float y = startY + (radius*i);
			circle(height/2, y, radius);


			if(limbs == true){
				line(width/2 - radius/2, y, width/2 - radius/2 - 20, y);
				line(width/2 + radius/2, y, width/2 + radius/2 + 20, y);
			}

		}


		textAlign(CENTER, CENTER);
		textSize(30);
		fill(color,255,255);
		text(name, width/2, startY - 80);
	}


	public void draw()
	{	
		background(0);
		drawNematodes();
	}
}
