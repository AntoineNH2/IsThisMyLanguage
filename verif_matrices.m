%% Verif des matrices
function verif_matrices
load('stat_fr.mat');
%%

ligne.lettre = stat.lettre(double('e'),[97:122]);
colonne.lettre = stat.lettre([97:122],double('e'));

ligne.start = stat.start_1;





function i=alea_perso(p, loi)
x=rand*95 +1;
i =1;
somme = loi(i);
while x<somme
    i=i+1;
    somme = somme + loi(i);
end

