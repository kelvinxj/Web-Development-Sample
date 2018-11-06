package com.kelvin.test;

public class StringUtils {
	public static boolean compareIgnoreCaseWithTrim(String str1, String str2)
	/*     */   {
	/* 344 */     if (str1 == null) {
	/* 345 */       str1 = "";
	/*     */     }
	/*     */     
	/* 348 */     if (str2 == null) {
	/* 349 */       str2 = "";
	/*     */     }
	/*     */     
	/* 352 */     return str1.trim().equalsIgnoreCase(str2.trim());
	/*     */   }
}
