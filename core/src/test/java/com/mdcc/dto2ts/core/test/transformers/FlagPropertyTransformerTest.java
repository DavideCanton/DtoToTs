package com.mdcc.dto2ts.core.test.transformers;

import com.mdcc.dto2ts.core.context.*;
import com.mdcc.dto2ts.core.test.*;
import com.mdcc.dto2ts.core.transformers.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlagPropertyTransformerTest extends BaseUnitTestClass
{
    @InjectMocks
    private FlagPropertyTransformer transformer;

    @Test
    public void testShouldTransformCorrectly()
    {
        PropertyRef propertyRef = mock(PropertyRef.class, "propertyRef");
        PropertyRef propertyRef2 = mock(PropertyRef.class, "propertyRef2");

        when(infoExtractor.getPropertyName(propertyRef)).thenReturn("flgproperty");
        when(propertyRefTransformer.makeBoolean(propertyRef)).thenReturn(propertyRef2);

        PropertyContext ctx = PropertyContext.builder()
            .className("className")
            .propertyRef(propertyRef)
            .propertyOperationsFactory(factory)
            .build();

        Optional<PropertyContext> result = transformer.transformProperty(ctx);

        assertTrue(result.isPresent());

        PropertyRef resultProperty = result.get().getPropertyRef();

        assertEquals(resultProperty, propertyRef2);
    }

    @Test
    public void testShouldNotTransformIfNotStartingWithPrefix()
    {
        PropertyRef propertyRef = mock(PropertyRef.class, "propertyRef");

        when(infoExtractor.getPropertyName(propertyRef)).thenReturn("property");

        PropertyContext ctx = PropertyContext.builder()
            .className("className")
            .propertyRef(propertyRef)
            .propertyOperationsFactory(factory)
            .build();

        Optional<PropertyContext> result = transformer.transformProperty(ctx);

        assertFalse(result.isPresent());
    }
}
