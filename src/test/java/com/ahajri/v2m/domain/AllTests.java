package com.ahajri.v2m.domain;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({PersonIntegrationTest.class,SenderReceiverIntegrationTest.class,VoiceFileIntegrationTest.class,MessageIntegrationTest.class})
public class AllTests {

}
