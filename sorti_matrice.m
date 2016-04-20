%%
global langue
load(['stat_', langue, '.mat']);

%%
[x1,x2]=size(stat.taille);

dblArray = javaArray('java.lang.Double',x1,x2);
    
for m = 1:x1
    for n = 1:x2
        dblArray(m,n) = java.lang.Double(stat.taille(m,n));
    end
end