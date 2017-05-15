package com.letstalkdata.iscream.service;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertTrue;


/**
 * Author: Phillip Johnson
 * Date: 5/10/17
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DailySpecialService.class)
public class DailySpecialServiceTestPowerMock {

    @Test
    public void mockStaticWithEasyMock() throws Exception {
        PowerMock.mockStatic(DailySpecialService.class);
        EasyMock.expect(DailySpecialService.isServiceAvailable())
                .andReturn(true);
        PowerMock.replay(DailySpecialService.class);

        boolean available = DailySpecialService.isServiceAvailable();
        assertTrue(available);
    }

    @Test
    public void mockStaticWithMockito() throws Exception {
        PowerMockito.mockStatic(DailySpecialService.class);
        Mockito.when(DailySpecialService.isServiceAvailable()).thenReturn(true);

        boolean available = DailySpecialService.isServiceAvailable();
        assertTrue(available);
    }
}
