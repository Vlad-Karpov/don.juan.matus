/*******************************************************************************
                    left rotation
                    
             dad (db)                        dad (dbn)
           /    \                          /       \
          /      \                        /         \
        /\        current (cb)           /\          pivot (pbn)
       /dl\      /\     \               /dl\       /         /\
      /____\    /cl\     \             /____\     /         /pr\
               /____\    pivot (pb)          current (cbn) /____\
                        /\     /\            /\      /\
                       /pl\   /pr\          /cl\    /pl\
                      /____\ /____\        /____\  /____\
                      
          
db(n) dad balance factor (new)
cb(n) current balance factor (new)          
pb(n) pivot balance factor (new)

dl dad left subtree
cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/

/*
n, a, b - integer

0) max(a, b) = max(b, a)
1) n + max(a, b) = max(n + a, n + b)
2) n * max(a, b) = max(n * a, n * b)   n >= 0
3) n * max(a, b) = min(n * a, n * b)   n < 0

0) min(a, b) = min(b, a)
1) n + min(a, b) = min(n + a, n + b)
2) n * min(a, b) = min(n * a, n * b)   n >= 0
3) n * min(a, b) = max(n * a, n * b)   n < 0
*/
  
 pb = H(pl) - H(pr)
    ->  H(pr) = H(pl) - pb
    ->  H(pl) = H(pr) + pb
 cb = H(cl) - (1 + max(H(pl), H(pr)))
    ->  H(cl) = max(H(pl), H(pr)) + 1 + cb 

cbn?  H(cl) - H(pl)
pbn?  (max(H(cl), H(pl)) + 1) - H(pr)

--------------------------------- cbn ------------------------------------------

cbn = max(pb, 0) + 1 + cb - pb
 

SELECT pb.b pbb, cb.b cbb, (maxx(pb.b, 0) + 1 + cb.b - pb.b) cbn
  FROM (SELECT -2 b FROM DUAL
        UNION
        SELECT -1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION 
        SELECT 1 b FROM DUAL) pb
        
/*        
pb    cb    cbn
-1    -2     0
 0    -2    -1
 1    -2    -1
-1    -1     1
 0    -1     0
 1    -1     0
*/
        
--------------------------------- pbn ------------------------------------------

 pb = H(pl) - H(pr)     ->  H(pr) = H(pl) - pb   ->  H(pl) = H(pr) + pb
 cb = H(cl) - (1 + max(H(pl), H(pr))) - > H(cl) = max(H(pl), H(pr)) + 1 + cb  

pbn?  (max(H(pl), H(cl)) + 1) - H(pr)

max(H(pl), H(cl)) + 1 - H(pr)
max(H(pl) + 1 - H(pr), H(cl)+ 1 - H(pr))
max(pb, H(cl) - H(pr)) + 1
max(pb, max(pb, 0) + 1 + cb) + 1

pbn = max(pb, max(pb, 0) + 1 + cb) + 1


SELECT pb.b pbb, cb.b cbb, (maxx(pb.b, maxx(pb.b, 0) + 1 + cb.b) + 1) pbn
  FROM (SELECT -2 b FROM DUAL
        UNION
        SELECT -1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION
        SELECT 1 b FROM DUAL) pb

/*
pb    cb    pbn    
-1    -2    0
 0    -2    1
 1    -2    2
-1    -1    1
 0    -1    1
 1    -1    2        
*/

/*******************************************************************************
                    right rotation
       
          current (cb)           pivot (pbn)    
         /          /\           /\       \
        /          /cr\         /pl\       \
    pivot (pb)    /____\       /____\     current (cbn)    
   /\     /\                              /\        /\          
  /pl\   /pr\                            /pr\      /cr\
 /____\ /____\                          /____\    /____\
          
          
cb(n) current balance factor (new)          
pb(n) pivot balance factor (new)

cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/

 pb = H(pl) - H(pr)
    ->   H(pr) = H(pl) - pb
    ->   H(pl) = H(pr) + pb
 cb = max(H(pl), H(pr)) + 1 - H(cr)
    ->   H(cr) = max(H(pl), H(pr)) + 1 - cb

cbn?  H(pr) - H(cr)
pbn?  H(pl) - (max(H(pr), H(cr)) + 1) 

--------------------------------- cbn ------------------------------------------

cbn = min(0, pb) - 1 + cb - pb 

SELECT pb.b pbb, cb.b cbb, (minn(0, pb.b) - 1 + cb.b - pb.b) cbn
  FROM (SELECT 2 b FROM DUAL
        UNION
        SELECT 1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION 
        SELECT 1 b FROM DUAL) pb
        
/*
pb    cb    cbn
-1    2     1
 0    2     1
 1    2     0
-1    1     0
 0    1     0
 1    1    -1
*/        

--------------------------------- pbn ------------------------------------------

 pb = H(pl) - H(pr)
    ->   H(pr) = H(pl) - pb
    ->   H(pl) = H(pr) + pb
 cb = max(H(pl), H(pr)) + 1 - H(cr)
    ->   H(cr) = max(H(pl), H(pr)) + 1 - cb

pbn?  H(pl) - (max(H(pr), H(cr)) + 1) 

pbn = min(pb, min(0, pb) - 1 + cb) - 1

SELECT pb.b pbb, cb.b cbb, (minn(pb.b, minn(0, pb.b) - 1 + cb.b) - 1) pbn
  FROM (SELECT 2 b FROM DUAL
        UNION
        SELECT 1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION 
        SELECT 1 b FROM DUAL) pb

/*
PB, CB, PBN
-1,  1,  -2
 0,  1,  -1
 1,  1,  -1
-1,  2,  -2
 0,  2,  -1
 1,  2,   0
*/



CREATE OR REPLACE FUNCTION minn (a1 NUMBER, a2 NUMBER)
   RETURN NUMBER
AS
BEGIN
   IF a1 < a2
   THEN
      RETURN a1;
   ELSE
      RETURN a2;
   END IF;
END;

CREATE OR REPLACE FUNCTION maxx (a1 NUMBER, a2 NUMBER)
   RETURN NUMBER
AS
BEGIN
   IF a1 > a2
   THEN
      RETURN a1;
   ELSE
      RETURN a2;
   END IF;
END;






/*******************************************************************************
                    left rotation current is right
                    
             dad (db)                        dad (dbn)
           /    \                          /       \
          /      \                        /         \
        /\        current (cb)           /\          pivot (pbn)
       /dl\      /\     \               /dl\       /         /\
      /____\    /cl\     \             /____\     /         /pr\
               /____\    pivot (pb)          current (cbn) /____\
                        /\     /\            /\      /\
                       /pl\   /pr\          /cl\    /pl\
                      /____\ /____\        /____\  /____\
                      
          
db(n) dad balance factor (new)
cb(n) current balance factor (new)          
pb(n) pivot balance factor (new)

dl dad left subtree
cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/
db =  H(dl) - max(H(cl)+ 1, H(pl) + 2, H(pr) + 2)
dbn = H(dl) - max(H(cl)+ 2, H(pl) + 2, H(pr) + 1)

db =  H(dl) - max(H(cl) - 1, H(pl), H(pr)) + 2
dbn = H(dl) - max(H(cl), H(pl), H(pr) - 1) + 2

dbn - db = max(H(cl) - 1, H(pl), H(pr)) - max(H(cl), H(pl), H(pr) - 1)
dbn - db = max(H(cl) - 1, H(pl), H(pr)) - max(H(cl), H(pl), H(pr) - 1) + H(pl) - H(pl)
dbn - db = max(H(cl) - 1, H(pl), H(pr)) + min(-H(cl), -H(pl), -H(pr) + 1) + H(pl) - H(pl)
dbn - db = max(H(cl) - H(pl)- 1, H(pl)- H(pl), H(pr)- H(pl)) + min(+ H(pl)-H(cl), + H(pl)-H(pl), + H(pl)-H(pr) + 1)
dbn - db = max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)
dbn = db + max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)


/*******************************************************************************
                    right rotation current is left
       
                     dad (db)                       dad (dbn)
                    /        \                   /           \
                   /         /\                 /            /\
          current (cb)      /dr\             pivot (pbn)    /dr\
          /          /\    /____\            /\       \    /____\
         /          /cr\                    /pl\       \
     pivot (pb)    /____\                  /____\     current (cbn)    
     /\     /\                                        /\        /\          
    /pl\   /pr\                                      /pr\      /cr\
   /____\ /____\                                    /____\    /____\

cb(n) current balance factor (new)
pb(n) pivot balance factor (new)

cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/

db =  max(H(cr)+ 1, H(pr) + 2, H(pl) + 2) - H(dr)
dbn = max(H(cr)+ 2, H(pr) + 2, H(pl) + 1) - H(dr)

db =  max(H(cr) - 1, H(pr), H(pl)) - H(dr) + 2
dbn = max(H(cr), H(pr), H(pl) - 1) - H(dr) + 2

db - dbn = max(H(cr) - 1, H(pr), H(pl)) - max(H(cr), H(pr), H(pl) - 1)
db - dbn = max(H(cr) - 1, H(pr), H(pl)) + min(-H(cr), -H(pr), 1 - H(pl)) + H(pr) - H(pr)
db - dbn = max(- cbn - 1, 0, pb) + min(cbn, 0, 1 - pb)
1 rl) dbn = db - max(- cbn - 1, 0, pb) - min(cbn, 0, 1 - pb)

2 rr) dbn = db + max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)

pb = 1
cb = 2
db = -2
dbn = -1  !!!!! right answer !!!
cbn = 0

1 rl) dbn = -2 - max(- 0 - 1, 0, 1) - min(0, 0, 1 - 1) = - 2 - 1 - 0 = -3   wrong!

2 rr) dbn = -2 + max(- 0 - 1, 0, 1) + min(0, 0, 1 - 1) = -2 + 1 + 0 = -1    !!! that`s right!


/*******************************************************************************
                    right rotation  current is right

                     dad (db)                       dad (dbn)
                    /        \                   /           \
                   /          \                 /             \
                  /\       current (cb)        /\           pivot (pbn)
                 /dl\     /          /\       /dl\           /\       \
                /____\   /          /cr\     /____\         /pl\       \
                     pivot (pb)    /____\                  /____\     current (cbn)
                     /\     /\                                        /\        /\
                    /pl\   /pr\                                      /pr\      /cr\
                   /____\ /____\                                    /____\    /____\

cb(n) current balance factor (new)
pb(n) pivot balance factor (new)

cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree

*******************************************************************************/

db =  H(dl) - max(H(cr) + 1, H(pr) + 2, H(pl) + 2)
dbn = H(dl) - max(H(cr) + 2, H(pr) + 2, H(pl) + 1)

db =  H(dl) - max(H(cr) - 1, H(pr), H(pl)) - 2
dbn = H(dl) - max(H(cr), H(pr), H(pl) - 1) - 2

dbn - db = max(H(cr) - 1, H(pr), H(pl)) - max(H(cr), H(pr), H(pl) - 1)  + H(pr) - H(pr)
dbn - db = max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)
dbn = db + max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)

dbn = db + max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)



/*******************************************************************************
                    left rotation current is left

             dad (db)                            dad (dbn)
           /        \                          /         \
          /          \                        /           \
    current (cb)     /\                    pivot (pbn)    /\
   /\     \         /dl\                 /         /\    /dl\
  /cl\     \       /____\               /         /pr\  /____\
 /____\    pivot (pb)              current (cbn) /____\
           /\     /\                /\      /\
          /pl\   /pr\              /cl\    /pl\
         /____\ /____\            /____\  /____\


db(n) dad balance factor (new)
cb(n) current balance factor (new)
pb(n) pivot balance factor (new)

dl dad left subtree
cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree

*******************************************************************************/

db =  max(H(cl)+ 1, H(pl) + 2, H(pr) + 2) - H(dl)
dbn = max(H(cl)+ 2, H(pl) + 2, H(pr) + 1) - H(dl)

db =  max(H(cl) - 1, H(pl), H(pr)) - H(dl) + 2
dbn = max(H(cl), H(pl), H(pr) - 1) - H(dl) + 2
dbn - db = max(H(cl), H(pl), H(pr) - 1) - max(H(cl) - 1, H(pl), H(pr))
dbn - db = max(H(cl), H(pl), H(pr) - 1) + min(-H(cl) + 1, -H(pl), -H(pr)) -H(pl) +H(pl)
dbn - db = max(H(cl)-H(pl), H(pl)-H(pl), H(pr)-H(pl) - 1) + min(+H(pl)-H(cl) + 1, +H(pl)-H(pl), +H(pl)-H(pr))
dbn - db = max(cbn, 0, - pb - 1) + min(1 - cbn, 0, pb)
dbn = db + max(cbn, 0, - pb - 1) + min(1 - cbn, 0, pb)



/*******************************************************************************/

-- right rotation
-- current left
dbn = db - max(- cbn - 1, 0, pb) - min(cbn, 0, 1 - pb)
-- current right
dbn = db + max(- cbn - 1, 0, pb) + min(cbn, 0, 1 - pb)

--left rotation
-- current left
dbn = db + max(cbn, 0, - pb - 1) + min(1 - cbn, 0, pb)
-- current right
dbn = db + max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)


