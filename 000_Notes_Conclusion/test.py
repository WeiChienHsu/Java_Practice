

def findNewName(self):
  for oldname in mydict.keys():
    if(oldname == self):
      return mydict[oldname]
  return "NotFound"


import os
import glob

files = glob.glob("CSE*.jpg")

for file in files:
  new_name = findNewName(file[:-4]) + ".jpg"
  os.rename(file, str(findNewName))
 