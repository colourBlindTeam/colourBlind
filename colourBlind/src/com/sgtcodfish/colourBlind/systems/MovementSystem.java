package com.sgtcodfish.colourBlind.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.sgtcodfish.colourBlind.components.Position;
import com.sgtcodfish.colourBlind.components.Velocity;
import com.sgtcodfish.colourBlind.components.Weight;

/**
 * Very simply handles the movement of entities by adding their velocity to
 * their position, after handling the effects of gravity or any other
 * accelerations.
 * 
 * @author Ashley Davis (SgtCoDFish)
 */
public class MovementSystem extends EntityProcessingSystem {
	public static final float	GRAVITY	= 1.0f;

	@Mapper
	ComponentMapper<Position>	pm		= null;
	@Mapper
	ComponentMapper<Velocity>	vm		= null;
	@Mapper
	ComponentMapper<Weight>		wm		= null;

	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Aspect.getAspectForAll(Position.class, Velocity.class));
	}

	public MovementSystem(Aspect aspect) {
		super(aspect);
	}

	@Override
	protected void process(Entity e) {
		Vector2 position = pm.get(e).position;
		Vector2 velocity = vm.get(e).velocity;
		Weight w = wm.get(e);

		if (w != null) {
			velocity.y += w.weight * GRAVITY;
		}

		position.add(velocity.scl(world.getDelta()));
	}
}
