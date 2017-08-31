package insomnia.builder;

import insomnia.factory.Factory;

public abstract class BuilderDataFactory extends Factory
{
	@Override
	abstract public BuilderData create();

}
