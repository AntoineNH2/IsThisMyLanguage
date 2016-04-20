%% Crée le tableau
%function map=creer_map(langue)  %à mettre en string 's'

global langue stat
langue = 'FR';
filepath = [num2str(langue), '.txt'];

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
stat.taille(1:100,1) = [1:100];
stat.taille(1:100,2) = 0;

stat.lettres_1 = zeros(255);    %lettre +1  
stat.lettres_2 = zeros(255);    %lettre +2
stat.start_1 = zeros(255,1);    %lettre qui commence +1
stat.start_2 = zeros(255);    %lettre qui commence +2

%%
dim_dic =size(C{1}); %les mots
k=0;
while k<dim_dic(1)
    k=k+1;
    if mod(k,500)==0
        disp(['Etape n°',num2str(k)]);
    end
    mot = C{1}{k};
    dim_mot= size(mot);
    i=1;
    while i<=dim_mot(2) 
        if double(mot(i))>255
            k=k+1;
            %disp(['Etape n°',num2str(k)]);
            mot = C{1}{k};
            dim_mot= size(mot);
            i=1;
        end
        %enregistre les stat
        if i == 1%start+1
            stat.start_1(double(mot(i)))=stat.start_1(double(mot(i)))+1;
        else 
            if i==2; %start+2
                stat.start_2(double(mot(i-1)),double(mot(i)))=stat.start_2(double(mot(i-1)),double(mot(i)))+1;
            else %data totale
                stat.lettres_1(double(mot(i-1)),double(mot(i)))=stat.lettres_1(double(mot(i-1)),double(mot(i)))+1;
                stat.lettres_2(double(mot(i-2)),double(mot(i)))=stat.lettres_2(double(mot(i-2)),double(mot(i)))+1;
            end        
            if mot(i)=='/';
                mot(i:dim_mot(2)) = [];
                break
            end
        end
        i=i+1;
    end
    %taille mot
    C{1}{k}=mot;
    stat.taille(size(mot,2),2) = stat.taille(size(mot,2),2) + 1;
    
    
end
save(['stat_', num2str(langue),'.mat'], 'langue', 'stat')

%% Plot les tableaux
nom = stat.taille;
figure(1);bar(nom(1:40,1),nom(1:40,2)/max(nom(1:40,2)))
title('Repartition longueurs de mots')

start1 = stat.start_1;
figure(2);bar(start1(:,1),start1(:,2)/max(start1(:,2)))
title('Repartition des premieres lettres')

%end