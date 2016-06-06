from bs4 import BeautifulSoup

f = open('tmp/a430e2798f2840a5174c9421905ff636f608f1f9', 'r')
content = f.read()

soup = BeautifulSoup(content, 'html.parser')

for article in soup.find_all('article'):
    titleregion = article.select('h2.cb-post-title a')
    print(titleregion[0]['href'])
    print(titleregion[0].get_text())
    time = article.select('time.updated')
    print(time[0].get('datetime'))
    print('----------------------')
