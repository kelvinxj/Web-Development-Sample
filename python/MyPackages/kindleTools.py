import xml.etree.ElementTree as ET

def getAnchorName(xmlString):
    aName = ""
    root = ET.fromstring(xmlString)
    for child in root:
        if(child.tag == "a"):
            aName = child.attrib["name"]
    return aName

def getAnchorText(xmlString):
    aText = ""
    root = ET.fromstring(xmlString)
    for child in root:
        if(child.tag == "a"):
            aText = "".join(child.itertext())
    return aText

def createTocByFileNames(fileNameList):
    isShowH3 = False
    ncxFileName = "toc.ncx"
    tocFileName = "toc.html"
    aName = ""
    aText = ""
    isFirstH1 = True
    f=open(ncxFileName,"w",encoding="utf-8")
    f.write('<?xml version="1.0"?>\n')
    f.write('<!DOCTYPE ncx PUBLIC "-//NISO//DTD ncx 2005-1//EN"   "http://www.daisy.org/z3986/2005/ncx-2005-1.dtd">\n')
    f.write('<ncx xmlns="http://www.daisy.org/z3986/2005/ncx/" version="2005-1">\n')
    f.write('   <head>\n')
    f.write('   </head>\n')
    f.write('   <docTitle>\n')
    f.write('       <text>KF8</text>\n')
    f.write('   </docTitle>\n')
    f.write('   <navMap>\n')
    f.write('       <navPoint>\n')
    f.write('           <navLabel><text>Table of Contents</text></navLabel>\n')
    f.write('           <content src="toc.html"/>\n')
    f.write('       </navPoint>\n')

    f1=open(tocFileName,'w',encoding='utf-8')
    f1.write('<!DOCTYPE html>\n')
    f1.write('<html xmlns="http://www.w3.org/1999/xhtml">\n')
    f1.write('<head>\n')
    f1.write('<title>TOC</title>\n')
    f1.write('</head>\n')
    f1.write('<body>\n')
    f1.write('  <h1 id="toc">Table of Contents</h1>\n')
    f1.write('  <ul>\n')
    #for each <h1/2> extract link name
    for fileName in fileNameList:
        isFirstH1 = True
        fileObj = open(fileName,"r",encoding="utf-8")
        for line in fileObj:
            line = line.strip()
            if(line.startswith("<h1")):
                if(not(isFirstH1)):
                    #if encounter with new <h1>, print close tag
                    print("not firstH1, title:" + line)
                    f.write( "      </navPoint>\n")

                    f1.write('          </ul>\n')
                    f1.write('      </li>\n')
                isFirstH1 = False
                aName = getAnchorName(line)
                aText = getAnchorText(line)
                f.write("       <navPoint>\n")
                f.write("           <navLabel><text>" + aText + "</text></navLabel>\n")
                f.write('           <content src="' + fileName + '#' + aName + '"/>\n')

                f1.write('      <li><a href="' + fileName + '#' + aName + '">' + aText + '</a>\n')
                f1.write('          <ul>\n')
            elif(line.startswith("<h2")):
                aName = getAnchorName(line)
                aText = getAnchorText(line)
                f.write("           <navPoint>\n")
                f.write("               <navLabel><text>" + aText + "</text></navLabel>\n")
                f.write('               <content src="' + fileName + '#' + aName + '"/>\n')
                f.write("           </navPoint>\n")

                f1.write('              <li><a href="' + fileName + '#' + aName + '">' + aText + '</a></li>\n')
        f.write("      </navPoint>\n")
        
        f1.write('          </ul>\n')
        f1.write('      </li>\n')
        
        fileObj.close()       

        
    f1.write('  </ul>\n')
    f1.write('</body>\n')
    f1.write('</html>')
    f1.close()
    
    f.write('   </navMap>\n')
    f.write('</ncx>')
    f.close()

def createTocBySingleFileName(fileName):
    isShowH3 = False
    ncxFileName = "toc.ncx"
    tocFileName = "toc.html"
    fileObj = open(fileName)
    aName = ""
    aText = ""
    isFirstH1 = True
    f=open(ncxFileName,"w")
    f.write('<?xml version="1.0"?>\n')
    f.write('<!DOCTYPE ncx PUBLIC "-//NISO//DTD ncx 2005-1//EN"   "http://www.daisy.org/z3986/2005/ncx-2005-1.dtd">\n')
    f.write('<ncx xmlns="http://www.daisy.org/z3986/2005/ncx/" version="2005-1">\n')
    f.write('   <head>\n')
    f.write('   </head>\n')
    f.write('   <docTitle>\n')
    f.write('       <text>KF8</text>\n')
    f.write('   </docTitle>\n')
    f.write('   <navMap>\n')

    f1=open(tocFileName,'w')
    f1.write('<!DOCTYPE html>\n')
    f1.write('<html xmlns="http://www.w3.org/1999/xhtml">\n')
    f1.write('<head>\n')
    f1.write('<title>TOC</title>\n')
    f1.write('</head>\n')
    f1.write('<body>\n')
    f1.write('  <h1 id="toc">Table of Contents</h1>\n')
    f1.write('  <ul>\n')
    #for each <h1/2> extract link name
    for line in fileObj:
        line = line.strip()
        if(line.startswith("<h1")):
            if(not(isFirstH1)):
                #if encounter with new <h1>, print close tag
                f.write( "      </navPoint>\n")

                f1.write('          </ul>\n')
                f1.write('      </li>\n')
            isFirstH1 = False
            aName = getAnchorName(line)
            aText = getAnchorText(line)
            f.write("       <navPoint>\n")
            f.write("           <navLabel><text>" + aText + "</text></navLabel>\n")
            f.write('           <content src="' + fileName + '#' + aName + '"/>\n')

            f1.write('      <li><a href="' + fileName + '#' + aName + '">' + aText + '</a>\n')
            f1.write('          <ul>\n')
        elif(line.startswith("<h2")):
            aName = getAnchorName(line)
            aText = getAnchorText(line)
            f.write("           <navPoint>\n")
            f.write("               <navLabel><text>" + aText + "</text></navLabel>\n")
            f.write('               <content src="' + fileName + '#' + aName + '"/>\n')
            f.write("           </navPoint>\n")

            f1.write('              <li><a href="' + fileName + '#' + aName + '">' + aText + '</a></li>\n')
    f.write("      </navPoint>\n")
    f.write('   </navMap>\n')
    f.write('</ncx>')
    f.close()

    
    f1.write('          </ul>\n')
    f1.write('      </li>\n')
    f1.write('  </ul>\n')
    f1.write('</body>\n')
    f1.write('</html>')
    f1.close()
    #print "create table of content"
    
#following code will be executed when call this file by command line "python kindleTools.py"
if __name__ == "__main__":
    print ("this file is called by command:", " python kindleTools.py")
