import sys
sys.path.append(r"C:\Users\IBM_ADMIN\Documents\MySource\python")

import MyPackages.kindleTools
#MyPackages.kindleTools.createToc("io.html")
a = MyPackages.kindleTools
a.createTocByFileNames(["TIEJv1.1.htm"])