package com.crypt;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Mummy extends Entity
{	
	Random randomGenerator = new Random();
	private WorldController worldController;
	
	public Mummy(WorldController worldController, Vector2 position, Animation[] animation, LevelMap levelMap, int spawnSiteID, boolean active)
	{	
		super(position, animation, new Vector2(8,2), new Vector2(-8,-2), spawnSiteID, active);
		
		CHAR_SPEED = 80 * Constant.GAME_SPEED;
		
		//Set velocity of mummy at spawn, if 0,0: do it again.
		velocity.x = randomGenerator.nextInt(2)-1;
		velocity.y = randomGenerator.nextInt(2)-1;		
		
		while (velocity.x == 0.0 && velocity.y == 0.0) 
		{
			velocity.x = randomGenerator.nextInt(3)-1;
			velocity.y = randomGenerator.nextInt(3)-1;
		}
		
		this.levelMap = levelMap;
		this.worldController = worldController;
	}
	
	@Override
	public void changeDirection() 	
	{	
		Vector2 characterPosition = new Vector2(worldController.getCharacterPosition());//character.getCharacterPosition();
		
		if(randomGenerator.nextInt(5) < 4)
		{
			if (bounds.x < characterPosition.x)
			{
				//System.out.println("Moving right");
				velocity.x = 1;
			}
			else if (bounds.x > characterPosition.x)
			{
				//System.out.println("Moving left");
				velocity.x = -1;
			}
			if (bounds.y < characterPosition.y)
			{
				//System.out.println("Moving up");
				velocity.y = 1;
			}
			else if (bounds.y < characterPosition.y)
			{
				//System.out.println("Moving down");
				velocity.y = -1;
			}
		}	
		else
		{
			velocity.x = randomGenerator.nextInt(3)-1;
			velocity.y = randomGenerator.nextInt(3)-1;
		}
		
		while (velocity.x == 0.0 && velocity.y == 0.0) 
		{
			//System.out.println("random");
			velocity.x = randomGenerator.nextInt(3)-1;
			velocity.y = randomGenerator.nextInt(3)-1;
		}
	}

	@Override
	void randomlyChangeDirection() 
	{	
		if (randomGenerator.nextInt(5) == 1) 
		{
			changeDirection();
		}
	}
}
