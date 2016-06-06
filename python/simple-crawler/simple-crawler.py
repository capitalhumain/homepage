import urllib2
import hashlib

print('Starting Simple Crawler ...');

opener = urllib2.build_opener()
# using urllib2 default User-agent -> HTTP 403
opener.addheaders = [('User-agent', 'Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11')]

# open url
target = 'http://www.digitalmusicnews.com/?s=swift'
f = opener.open(target)

# read all content
rawcontent = f.read()

downloadfile = hashlib.sha1(target).hexdigest()
savefile = open('./tmp/' + downloadfile, 'w')
savefile.write(rawcontent)
savefile.close()

print('url:' + target + ' Write ' + downloadfile + ' Done.')
