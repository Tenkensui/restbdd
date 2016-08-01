package com.moatcrew.restbdd.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by E-JENN on 01/08/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJbehaveStory extends JUnitStories {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), applicationContext);
    }

    @Override
    protected List<String> storyPaths() {
        String targetStory = System.getProperty("targetStory") == null ? "*" : System.getProperty("targetStory");
        return new StoryFinder().
                findPaths(CodeLocations.codeLocationFromClass(
                        this.getClass()),
                        Arrays.asList("**/" + targetStory + ".story"),
                        Arrays.asList(""));
    }

    @Override
    public Configuration configuration(){
        return new MostUsefulConfiguration()
                .useStoryLoader(storyLoader());
    }

    private StoryLoader storyLoader(){ return new LoadFromClasspath();}
}


