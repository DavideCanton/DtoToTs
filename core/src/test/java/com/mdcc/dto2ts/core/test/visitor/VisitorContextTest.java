package com.mdcc.dto2ts.core.test.visitor;

import com.mdcc.dto2ts.core.visitor.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class VisitorContextTest
{
    private VisitorContext context;

    @BeforeEach
    public void setup()
    {
        context = new VisitorContext();
    }

    @Test
    public void shouldRegister()
    {
        assertThat(context.getVisitedClasses(), empty());

        context.addClass("c1");
        context.addClass("c2");
        context.addClass("c1");

        assertThat(context.getVisitedClasses(), hasSize(2));
        assertThat(context.getVisitedClasses(), hasItem("c1"));
        assertThat(context.getVisitedClasses(), hasItem("c2"));
    }
}
