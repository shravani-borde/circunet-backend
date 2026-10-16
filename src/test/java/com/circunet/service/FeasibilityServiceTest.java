package com.circunet.service;
import org.junit.jupiter.api.Test; import java.util.Map; import static org.junit.jupiter.api.Assertions.*;
class FeasibilityServiceTest { @Test void formulasAreApplied(){Map<String,Object> r=new FeasibilityService().calculate(100,10,600,800,900,1,.08,"test");Map<?,?> e=(Map<?,?>)r.get("economics");assertEquals(6000.0,e.get("revenueInr"));assertEquals(8000.0,e.get("avoidedDisposalInr"));assertEquals(2000.0,e.get("transportCostInr"));assertEquals(12000.0,e.get("netBenefitInr"));}}
