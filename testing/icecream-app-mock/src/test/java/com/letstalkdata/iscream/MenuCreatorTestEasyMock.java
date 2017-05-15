package com.letstalkdata.iscream;

import com.letstalkdata.iscream.service.DailySpecialService;
import org.easymock.EasyMock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Phillip Johnson
 * Date: 5/6/17
 */
public class MenuCreatorTestEasyMock {

    @Test
    public void WhenAMenuIsCreated_ThenDailySpecialServiceIsCalled() {
        // Step 1: Create the mock object
        DailySpecialService mockService =
                EasyMock.createMock(DailySpecialService.class);

        // Step 2: Set the expectations
        List<String> specials = new ArrayList<>();
        EasyMock.expect(mockService.getSpecials()).andReturn(specials).once();
        EasyMock.replay(mockService);

        // Step 3: Inject the mock
        MenuCreator menuCreator = new MenuCreator(mockService);

        // Step 4: Invoke the test object
        menuCreator.getTodaysMenu();

        // Step 5: Verify
        EasyMock.verify(mockService);
    }
}