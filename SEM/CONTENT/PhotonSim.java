package kuhn.domenik;

import java.io.FileWriter;
import java.io.IOException;

public class PhotonSim {

	public static void main(String[] args) {
		
		if(args.length<4){
			System.out.println("Syntax:");
			System.out.println("photonSim <StartPhotonen> <Anzahl Strahlenteiler> <Durchlass Warscheinlichkei> <Anzahl Durchlaeufe> [Output File Name]");
			System.exit(0);
		}
		System.out.println();
		
		int runs = Integer.parseInt(args[3]);
		int startPhotonen = Integer.parseInt(args[0]);
		int nStrahlenTeiler = Integer.parseInt(args[1]);
		double p = Double.parseDouble(args[2]);
		int[] endPhotonen = new int[runs];
		int[] absHaeufigkeit = new int[startPhotonen+1];
		
		for(int i=0; i<runs; i++){
			int nPhotonen = startPhotonen;
			for(int k=0; k<nStrahlenTeiler; k++){
				nPhotonen = strahlenTeiler(nPhotonen,p);
			}
			endPhotonen[i]=nPhotonen;
			absHaeufigkeit[nPhotonen]++;
		}
		
		if(args.length==5){
			csvWriter(args[4],absHaeufigkeit);
		}else{
			System.out.println("Durchgekommene Photnen\t\tAbsolute Haeufigkeit");
			for(int i = 0; i<startPhotonen+1;i++){
				System.out.println(i+"\t\t\t\t"+absHaeufigkeit[i]);
			}
		}
	}

	public static int strahlenTeiler(int photonen, double p){
		int sout=0;
		for(int i=0; i<photonen;i++){
			if(Math.random()<=p)sout++;
		}
		return sout;
	}
	
	public static void csvWriter(String path, int[] data){
		try {
			FileWriter fw = new FileWriter(path);
			for(int i=0; i<data.length; i++){
				fw.write(i+";"+data[i]+"\n");
			}
			fw.close();
		} catch (IOException e) {
		}
		
	}
}
