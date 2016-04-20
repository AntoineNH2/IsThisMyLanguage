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
stat.start_1 = zeros(255,2); stat.start_1(:,1)=[1:255];    %lettre qui commence +1
stat.start_2 = zeros(255);    %lettre qui commence +2

%%
dim_dic =size(C{1}); %les mots
k=0;
while k<dim_dic(1)
    k=k+1;
    if mod(k,2000)==0
%         disp(['Etape n°',num2str(k)]);
        disp('Calculs en cours...')
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
            stat.start_1(double(mot(i)),2)=stat.start_1(double(mot(i)),2)+1;
%             disp('start i=1 fait')
        else 
            %if i==2; %start+2
%                 disp('i=2 fait')
            %    stat.start_2(double(mot(i-1)),double(mot(i)))=stat.start_2(double(mot(i-1)),double(mot(i)))+1;
            stat.lettres_1(double(mot(i-1)),double(mot(i)))=stat.lettres_1(double(mot(i-1)),double(mot(i)))+1;

           % else %data totale
%                 disp('i>2 fait')
            if i>2
                %stat.lettres_1(double(mot(i-1)),double(mot(i)))=stat.lettres_1(double(mot(i-1)),double(mot(i)))+1;
                stat.lettres_2(double(mot(i-2)),double(mot(i)))=stat.lettres_2(double(mot(i-2)),double(mot(i)))+1;
            end        
            if mot(i)=='/';
%                 disp('mot(i) = /')
                mot(i:dim_mot(2)) = [];
                break
            end
        end
        i=i+1;
%         disp('fin de la lettre')
    end
    %taille mot
    C{1}{k}=mot;
    stat.taille(size(mot,2),2) = stat.taille(size(mot,2),2) + 1;
%     disp('fin du mot') 
end
disp('fin des calculs')
%% Normalisation
stat.taille = stat.taille/max(stat.taille(:,2));
stat.lettres_1 = stat.lettres_1/max(max(stat.lettres_1));
stat.lettres_2 = stat.lettres_2/max(max(stat.lettres_2));
stat.start_1(:,2) = stat.start_1(:,2)/max(stat.start_1(:,2));
% stat.start_2 = stat.start_2/max(max(stat.start_2));

%% Sauvegarde
save(['stat_', num2str(langue),'.mat'], 'langue', 'stat')

%% Plot les tableaux
figure(1);bar(stat.taille(1:40,1),stat.taille(1:40,2))%/max(nom(1:40,2)))
title('Repartition longueurs de mots')

figure(2); imagesc(stat.lettres_1(65:122,65:122))
title('Repartition lettre -1')

figure(3); imagesc(stat.lettres_2(65:122,65:122));
title('Repartition lettre -2')

figure(4);bar(stat.start_1(:,1),stat.start_1(:,2));%/max(start1(:,2)))
title('Repartition des premieres lettres')

% figure(5); imagesc(stat.start_2(65:122,65:122))
% title('Repartition des starts -2')

%%




%%
%end