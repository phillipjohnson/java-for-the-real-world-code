package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Author: Phillip Johnson
 * Date: 5/6/17
 */
public class MenuCreatorTestMockito {

    @Test
    public void WhenAMenuIsCreated_ThenDailySpecialServiceIsCalled() {
        // Step 1: Create the mock object
        DailySpecialService mockService =
                Mockito.mock(DailySpecialService.class);

        // Step 2: Set the expectations
        List<String> specials = new ArrayList<>();
        Mockito.when(mockService.getSpecials()).thenReturn(specials);

        // Step 3: Inject the mock
        MenuCreator menuCreator = new MenuCreator(mockService);

        // Step 4: Invoke the test object
        menuCreator.getTodaysMenu();

        // Step 5: Verify
        Mockito.verify(mockService, Mockito.times(1));
    }

    @Mock
    private DailySpecialService mockDailySpecialService;

    @Test
    public void menuCreatorCanBeReused() {
        MockitoAnnotations.initMocks(this);
        MenuCreator menuCreator = new MenuCreator(mockDailySpecialService);

        try {
            menuCreator.getTodaysMenu();
            menuCreator.getTodaysMenu();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Menu creators should be reusable!");
        }
    }
}