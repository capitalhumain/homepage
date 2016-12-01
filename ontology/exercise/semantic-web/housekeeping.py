import glob
import os
from shutil import rmtree

# relative directory version
if __name__ == '__main__':
  print("fuseki recyclebin housekeeping script")
  dbconf_files = glob.glob('./recyclebin/*.ttl')
  for dbconf_file in dbconf_files:
    prefixlen = len('./recyclebin/')
    #print(prefixlen)
    #print(dbconf_file)
    dbname = dbconf_file[prefixlen:prefixlen+36]
    #print(dbname) 
    dbfolder = './databases/' + dbname
    try:
      print('delete folder: ' + dbfolder)
      rmtree(dbfolder)
    except:
      print('delete folder failed: ' + dbfolder)
    finally:
      # remove ttl configuration file
      os.remove(dbconf_file)
      print('delete dataset configuration file: ' + dbconf_file)
