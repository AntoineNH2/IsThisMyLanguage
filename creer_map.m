%% Crée le tableau
%function map=creer_map(langue)  %à mettre en string 's'
clear all
global langue stat
langue = 'fr';
filepath = [num2str(langue), '_modif.txt'];
disp('Bonjour !')
disp('Récupération du dictionnaire')
fileID = fopen(filepath);
C = textscan(fileID,'%s %d');
fclose(fileID);
%celldisp(C)

% % lettre à la suite
% % lettre +2 à la suite
% % début de mot
% % fin de mot
% % taille des mots

%% String => ascii et ascii => string
% s=double('hello')
% 
% s =
% 
%    104 101 108 108 111
% 
% %Convert back as follows
% 
% >> [char(s)]
% 
% ans =
% hello

%% Variable pour les statistiques
%taille mot
stat.taille = zeros(50,1);

stat.lettres_1 = zeros(255);    %lettre +1  
stat.lettres_2 = zeros(255);    %lettre +2
% stat.lettre = zeros(255);   %ne marche pas    % prise en compte de 2/3 lettre1 et 1/3 lettre2
stat.start_1 = zeros(255,1);    %lettre qui commence +1


%%


tic
dim_dic =size(C{1}); %les mots
k=0;
disp('Calculs en cours...')
nb_supp = 0;

while k<dim_dic(1)
    k=k+1;
    if mod(k,2000)==0
%         disp(['Etape n°',num2str(k)]);
    end
    mot = C{1}{k};
    dim_mot= size(mot);
    i=1;
    while i<=dim_mot(2) 
        if (double(mot(i))>122 || (double(mot(i))<47) ||(double(mot(i))>47 && double(mot(i))<65)) || (double(mot(i))>90 && double(mot(i))<97) %ce n'est pas une lettre
            %disp(['Mot non pris en compte: ',mot])
            nb_supp = nb_supp +1;
            k=k+1;
            %disp(['Etape n°',num2str(k)])
            mot = C{1}{k};
            dim_mot= size(mot);
            i=1;
        end %on a sauté le mot
       
        %enregistre les stat
        if i == 1%start+1
            stat.start_1(double(mot(i)))= stat.start_1(double(mot(i)))+1;
%             disp('start i=1 fait')
        else
            if mot(i)=='/';
%                 disp('mot(i) = /')
                mot(i:dim_mot(2)) = [];
                break
            end
            stat.lettres_1(double(mot(i-1)),double(mot(i)))=stat.lettres_1(double(mot(i-1)),double(mot(i)))+1;
                           %(lettre precedente, lettre suivante)
            if i>2
                stat.lettres_2(double(mot(i-2)),double(mot(i)))=stat.lettres_2(double(mot(i-2)),double(mot(i)))+1;
            end
        end
        i=i+1;
%         disp('fin de la lettre')
    end
    %taille mot
    C{1}{k}=mot;
    stat.taille(size(mot,2)) = stat.taille(size(mot,2)) + 1;
%     disp('fin du mot') 
end
disp(['Nb de mot sautés: ', num2str(nb_supp), '/', num2str(dim_dic(1))])
disp('fin des calculs')
toc

%% Normalisation
% stat.taille =round(100* stat.taille/max(stat.taille));
% stat.lettres_1 =round(100* stat.lettres_1/max(max(stat.lettres_1)));
% stat.lettres_2 =round(100* stat.lettres_2/max(max(stat.lettres_2)));
% stat.lettre = stat.lettres_1 + stat.lettres_2;
% stat.start_1 = round(100*stat.start_1/max(stat.start_1));

%Calculs des totaux
tot.taille = sum(stat.taille);
tot.l1 = sum(stat.lettres_1')';
tot.l2 = sum(stat.lettres_2')';
% tot.lettre = sum(stat.lettre')';
tot.start = sum(stat.start_1);

% Mises en %age
stat.taille =round(100* stat.taille/(tot.taille));
for ligne =1:255
    if tot.l1(ligne) ~=0
%       stat.lettre(ligne,:) =round(100* stat.lettre(ligne,:)/(tot.lettre(ligne))); 
        stat.lettres_1(ligne,:) =round(100* stat.lettres_1(ligne,:)/(tot.l1(ligne)));
    end
    if tot.l2(ligne) ~=0
        stat.lettres_2(ligne,:) =round(100* stat.lettres_2(ligne,:)/(tot.l2(ligne)));
    end
end
stat.start_1 = round(100*stat.start_1/(tot.start));

%% Sauvegarde
save(['stat_', num2str(langue),'.mat'], 'langue', 'stat')

%% Plot les tableaux

% figure(1);bar(stat.taille)%/max(nom(1:40,2)))
% title('Repartition longueurs de mots')
% 
% figure(2); imagesc(stat.lettres_1(65:122,65:122))
% title('Repartition lettre -1')
% 
% figure(3); imagesc(stat.lettres_2(65:122,65:122));
% title('Repartition lettre -2')
% 
% figure(4);bar(stat.start_1);%/max(start1(:,2)))
% title('Repartition des premieres lettres')


<<<<<<< HEAD
<<<<<<< HEAD
%% Modif sur lettres 1 et 2 pour en faire des pourcentages sommés

stat.lettres_1b = stat.lettres_1;
for ligne = 1:size(stat.lettres_1,1)
    for col = size(stat.lettres_1,2):-1:2
        stat.lettres_1b(ligne,col) = sum(stat.lettres_1(ligne,1:col-1));
    end
    
   
end

=======
%% Modif sur lettres_2 pour en faire des pourcentages sommés
>>>>>>> 6a38fc2f4ea58889bef87631633bcc1cead07d61
stat.lettres_2b = stat.lettres_2;
for ligne = 1:size(stat.lettres_2,1)
    for col = size(stat.lettres_2,2):-1:2
        stat.lettres_2b(ligne,col) = sum(stat.lettres_2(ligne,1:col-1));
    end
    
end

<<<<<<< HEAD
=======
>>>>>>> parent of 6a38fc2... Modif sur creermap pour ameliorer les matrices. Il faut encore améliorer le système pour sortir des mots convenables... Notamment le système de probabilité !!
=======
>>>>>>> 6a38fc2f4ea58889bef87631633bcc1cead07d61
%%



dlmwrite(['taille_', langue, '.txt'],stat.taille)
% dlmwrite(['lettre_', langue, '.txt'],stat.lettre)
dlmwrite(['lettres_1_', langue, '.txt'],stat.lettres_1)
dlmwrite(['lettres_2_', langue, '.txt'],stat.lettres_2)
dlmwrite(['start_', langue, '.txt'], stat.start_1)

disp('Copie des fichiers')
disp(' ')
copyfile(['taille_', langue, '.txt'],'/home/antoine/IsThisMyLanguage/app/src/main/assets')
% copyfile(['lettre_', langue, '.txt'],'/home/antoine/IsThisMyLanguage/app/src/main/assets')
copyfile(['lettres_1_', langue, '.txt'],'/home/antoine/IsThisMyLanguage/app/src/main/assets')
copyfile(['lettres_2_', langue, '.txt'],'/home/antoine/IsThisMyLanguage/app/src/main/assets')
copyfile(['start_', langue, '.txt'], '/home/antoine/IsThisMyLanguage/app/src/main/assets')

%%
%end