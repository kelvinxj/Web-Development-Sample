#!/bin/usr/env python
# -*- coding: utf-8 -*-
from lxml import etree
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf-8')
htmlStr = """
<html>
	<head>
		<meta http-equiv="Content-Type"
				content="text/html;charset=utf-8">
		<meta http-equiv="X-UA-Compatible"
					content="IE=edge,chrome=1">
		<meta content="always"
						name="referrer">
		<meta name="theme-color"
							content="#2932e1">
		<meta name="description"
			content="全球最大的中文搜索引擎、致力于让网民更便捷地获取信息，找到所求。百度超过千亿的中文网页数据库，可以瞬间找到相关的搜索结果。">
		<link rel="shortcut icon"
				href="/favicon.ico"
				type="image/x-icon" />
		<link rel="search"
				type="application/opensearchdescription+xml"
				href="/content-search.xml"
				title="百度搜索" />
		<link rel="icon"
				sizes="any"
				mask href="//www.baidu.com/img/baidu_85beaf5496f291521eb75ba38eacbd87.svg">
		<link rel="dns-prefetch"
					href="//dss0.bdstatic.com"/>
		<link rel="dns-prefetch"
					href="//dss1.bdstatic.com"/>
		<link rel="dns-prefetch"
					href="//ss1.bdstatic.com"/>
		<link rel="dns-prefetch"
					href="//sp0.baidu.com"/>
		<link rel="dns-prefetch"
					href="//sp1.baidu.com"/>
		<link rel="dns-prefetch"
					href="//sp2.baidu.com"/>
		<title>百度一下，你就知道</title>
		<noscript>
				<meta http-equiv="refresh"
						content="0; url=http://www.baidu.com/baidu.html?from=noscript" />
		</noscript>
	</head>
</html>
"""

html = etree.HTML(htmlStr)
#top level html element
print(type(html)) #<class 'lxml.etree._Element'>

#get element head
headList = html.xpath("/html/head")
print(type(headList)) #<class 'list'>

head = headList[0]
print(type(head)) #<class 'lxml.etree._Element'>

#get all metas
metas = head.xpath("meta")
print("there are total " + str(len(metas)) + " metas in head element")

#access single node's attributes:
print(metas[0].attrib)

#retrieve value of attribute
for key in metas[0].attrib.keys():
    print("key:" + key + "; value:" + metas[0].attrib.get(key))
    
    
#retrieve the text of node:
title = head.xpath("title")[0]
print("title text:" + title.text)

#retrieve node by attribute name
meta = head.xpath("meta[@name='theme-color']")[0]
print("meta content attribute is:" + meta.attrib.get("content"))