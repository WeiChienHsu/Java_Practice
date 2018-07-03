myDict = {'Brian': 'lavitar', 'CSR10079' : 'earring'}

def findNewName(oldName):
    for name in myDict.keys():
      if(name == oldName):
        return myDict[name]
    return "Not Found"

files = ['CSR10079', 'CSR10079', 'Brian']

for file in files:
  print(findNewName(file) + ".jpg")



# import os
# import glob

# files = glob.glob("CSE*.jpg")

# for file in files:
#   nameName = findNewName(file) + ".jpg"
#   os.rename(file, findNewName)

