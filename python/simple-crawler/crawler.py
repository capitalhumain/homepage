# -*- coding: utf-8 -*-
import urllib2
import hashlib
import json
from bs4 import BeautifulSoup

def fetch(target):
    opener = urllib2.build_opener()
    opener.addheaders = [('User-agent', 'Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11')]
    f = opener.open(target)
    rawcontent = f.read()
    return rawcontent

def parse(content, site):
    ### 網站改版這隻就可能要大改
    result = list()
    soup = BeautifulSoup(content, 'html.parser')
    for article in soup.find_all('article'):
        item = dict()
        item['site'] = site
        titleregion = article.select('h2.cb-post-title a')
        #print(titleregion[0]['href'])
        item['url'] = titleregion[0]['href']
        #print(titleregion[0].get_text())
        item['title'] = titleregion[0].get_text()
        time = article.select('time.updated')
        #print(time[0].get('datetime'))
        item['datetime'] = time[0].get('datetime')
        result.append(item)
        #print('----------------------')
    return result

if __name__ == "__main__":
    print('Starting Crawler ...')
    # here
    site = 'Digital Music News'
    # here
    result = parse(fetch('http://www.digitalmusicnews.com/?s=justin'), site)
    print(json.dumps(result))
