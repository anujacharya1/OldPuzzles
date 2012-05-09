import sys

#FILE = "/Library/WebServer/CGI-Executables/Job Vite/jobvitetextfile.txt"
FILE = sys.argv[1]
GIVEN_WORD = "hello"
f, nf, w = set(), ["hello"], set(open(FILE).read().splitlines())

def next():
    f = []
    for b in nf:
        for i in range(len(b)): #for each index in b
            left, at, right = b[:i], b[i:], b[i+1:]
            for c in 'abcdefghijklmnopqrstuvwxyz':
                #substitute b[i] with c
                x = left + c + right
                if x in w: f.append(x)
                #inject c before b[i]
                x = left + c + at
                if x in w: f.append(x)
            #remove b[i]
            x = left + right
            if x in w: f.append(x)
        for c in 'abcdefghijklmnopqrstuvwxyz':
            x = (b + c)
            if x in w: f.append(x) #append c after b
    return set(f)

while nf:
    nf = next()
    w -= nf
    f |= nf

print "The social network for the word '"+GIVEN_WORD+"' is " + str(len(f)) + "."
#assert (len(f) == 78482)