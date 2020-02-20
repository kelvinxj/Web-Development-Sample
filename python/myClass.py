#!/usr/bin/env python
# -*- coding: utf-8 -*-

class myDeployUtil():
    #class variables shared by all class instances
    cbaFileName = "mycba.cba"
    
    #Mutable class variable shared by all class instances.
    data = []
    
    def showcba(self):
        print("start to deloy:" + self.cbaFileName)
    