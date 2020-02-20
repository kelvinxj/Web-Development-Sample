#!/usr/bin/env python
# -*- coding: utf-8 -*-

#import class file from myclass.py
from myClass import myDeployUtil

#for class variable, change it from instance level, only impact its value of current instance.
print("change class variable from instance level")
deploy1 = myDeployUtil()
#it will display: mycba.cba
deploy1.showcba()

deploy2 = myDeployUtil()
#changed class variable from instance level.
deploy2.cbaFileName = "deploy2.cba"
#it will display: deploy2.cba
deploy2.showcba()

#still display: mycba.cba
deploy1.showcba()

deploy3 = myDeployUtil()

#for class variable, change it from class level, will impact its value of all instances.
#if an instance changed its class variable, class level change will not take effect.
print("change class variable from class level")
myDeployUtil.cbaFileName = "changeForAllCBA.cba"
#will show changeForAllCBA.cba
deploy1.showcba()
#still show deploy2.cba as cbaFileName of instance deploy2 has been changed at line 15
deploy2.showcba()
#show changeForAllCBA.cba
deploy3.showcba()

#for mutable class variable such as list or dictionary, change it from instance level, will impact value of other instances
#using mutable variable as class variable should be avoided.
deploy1.data.append("deploy1")
deploy2.data.append("deploy2")

#both will display: ['deploy1', 'deploy2']
print(deploy1.data)
print(deploy2.data)