import java.util.Scanner;
public class TrainReservationProgram {
	static int nTrain;
	static Train train[];
	
	public static void main(String[] args) {
		nTrain = 3;
		train = new Train[nTrain];
		train[0] = new Train(1,1,30,2);
		train[1] = new Train(2,2,30,2);
		train[2] = new Train(3,3,30,2);
		showHome();
		boolean reservation_on = true;
		Scanner scanner = new Scanner(System.in);
		loop1: while (reservation_on) { // 예약 시스템 코드
			System.out.print("Select train number: ");
			int ntrain = scanner.nextInt();
			while (ntrain < 1 || ntrain > nTrain) { // invalid input 거르는 코드
				if (ntrain == -1) {
					reservation_on = false;
					break loop1;
				}
				System.out.println("Invalid train number");
				System.out.print("Reservation Continue? (y/n): ");
				String answer = scanner.next();
				if (answer.equals("y"))
					showHome();
				else if (answer.equals("-1")) {  // invalid input 거르는 코드
					reservation_on = false;
					break loop1;
				}
				else if (answer.equals("n")) {
					reservation_on = false;
					break loop1;
				}
				else {
					reservation_on = false;
					break loop1;
				}
				System.out.print("Select train number: ");
				ntrain = scanner.nextInt();
			}
			System.out.print("Select Executive(1)/Standard(2): ");
			int ncar = scanner.nextInt();
			while (ncar < 1 || ncar > 2) { // invalid input 거르는 코드
				if (ncar == -1) {
					reservation_on = false;
					break loop1;
				}
				System.out.println("Invalid condition. Input 1 or 2.");
				System.out.print("Reservation Continue? (y/n): ");
				String answer = scanner.next();
				if (answer.equals("y"))
					showHome();
				else if (answer.equals("-1")) { // invalid input 거르는 코드
					reservation_on = false;
					break loop1;
				}
				else if (answer.equals("n")) {
					reservation_on = false;
					break loop1;
				}
				else {
					reservation_on = false;
					break loop1;
				}
				System.out.print("Select Executive(1)/Standard(2): ");
				ncar = scanner.nextInt();
			}
			System.out.print("Select Window(1)/Aisle(2): ");
			int seat_type = scanner.nextInt();
			while (seat_type < 1 || seat_type > 2) { // invalid input 거르는 코드
				if (seat_type == -1) {
					reservation_on = false;
					break loop1;
				}
				System.out.println("Invalid condition. Input 1 or 2.");
				System.out.print("Reservation Continue? (y/n): ");
				String answer = scanner.next();
				if (answer.equals("y"))
					showHome();
				else if (answer.equals("-1")) {
					reservation_on = false;
					break loop1;
				}
				else if (answer.equals("n")) {
					reservation_on = false;
					break loop1;
				}
				else {
					reservation_on = false;
					break loop1;
				}
				System.out.print("Select Window(1)/Aisle(2): ");
				seat_type = scanner.nextInt();
			}
			boolean found_seat = false;
			while (!found_seat) { // 조건에 맞는 좌석 찾는 코드
			loop2: for (int i = 0; i < train[ntrain-1].car[ncar-1].nRow; i++) {
					for (int j = 0; j < 4; j++)
						if (seat_type == 1) { // 창가 자리 찾는 코드
							if (train[ntrain-1].car[ncar-1].seats[i][j].isWindow() == true) {
								if (train[ntrain-1].car[ncar-1].seats[i][j].reserved == false) {
									train[ntrain-1].car[ncar-1].seats[i][j].reserved = true;
									System.out.println("Your reservation completed");
									System.out.println("Train " + ntrain + " (" + train[ntrain-1].hour + ":" + train[ntrain-1].min + ") Car " + ncar + " Seat " + (i+1) + (char)('A' + j));
									found_seat = true;
									break loop2;
								}
							}
						} // end if
						else { // 복도 자리 찾는 코드
							if (train[ntrain-1].car[ncar-1].seats[i][j].isWindow() == false) {
								if (train[ntrain-1].car[ncar-1].seats[i][j].reserved == false) {
									train[ntrain-1].car[ncar-1].seats[i][j].reserved = true;
									System.out.println("Your reservation completed");
									System.out.println("Train " + ntrain + " (" + train[ntrain-1].hour + ":" + train[ntrain-1].min + ") Car " + ncar + " Seat " + (i+1) + (char)('A' + j));
									found_seat = true;
									break loop2;
								}
							}
						} // end else
				} //end for
				if (found_seat == false) {
					System.out.println("No seat available for the given conditions");
					found_seat = true;
				}
			}//end while
			System.out.print("Reservation Continue? (y/n): "); // 에약 실패/성공 후 계속 여부 묻는 코드
			String answer = scanner.next();
			if (answer.equals("y"))
				showHome();
			else if (answer.equals("-1")) {
				reservation_on = false;
				break loop1;
			}
			else if (answer.equals("n")) {
				reservation_on = false;
				break loop1;
			}
			else {
				reservation_on = false;
				break loop1;
			}
		} //end while(loop1)
		showHome();
		System.out.println("System Exit ...");		
	}
	static void showHome() {
		System.out.println();
		for (int i = 0; i < nTrain; i++) { //Train정보 인쇄
			System.out.println("*** Train " + (i+1) + " ***(" + train[i].hour + ":" + train[i].min + ")" + "  ex(" + train[i].avSeatEx() + ")  std(" + train[i].avSeatStd() + ")");
			for (int l = 0; l < train[i].nCar; l++) { //Train 마다 Car정보 인쇄
				if (train[i].car[l].executive == true)
					System.out.println("-- Car " + train[i].car[l].id + " (EX) --");
				else
					System.out.println("-- Car " + train[i].car[l].id + " --");
				for (int j = 0; j < train[i].car[l].nRow; j++) { //Seat 정보 인쇄
					for (int k = 0; k < 4; k++) {
						if (train[i].car[l].seats[j][k].reserved == true)
							System.out.print("X  ");
						else {
							System.out.print(j+1);
							System.out.print(((char)('A' + k)) + " "); // 좌석번호 인쇄
						}
					}
					if (j == 0)
						System.out.print("   |   ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}

class Train{
	int id;
	int hour;
	int min;
	int nCar;
	Car car[];
	
	Train(){}
	Train(int id, int hour, int min, int nCar){
		this.id = id;
		this.hour = hour;
		this.min = min;
		this.nCar = nCar;
		car = new Car[nCar];
		for (int i = 0; i < nCar; i++) {
			if (i == 0)
				car[i] = new Car(i+1,true,2);
			else
				car[i] = new Car(i+1,false,2);
		}
	}
	
	int avSeatEx() {return this.car[0].avSeatEx();}
	int avSeatStd() {
		int sum = 0;
		for (int i = 1; i < nCar; i++) {
			sum += this.car[i].avSeatStd();
		}
		return sum;
	}
}

class Car{
	int id;
	boolean executive;
	int nRow;
	Seat seats[][];
	
	Car() {}
	Car(int id, boolean executive,int nRow){
		this.id = id;
		this.executive = executive;
		this.nRow = nRow;
		seats = new Seat[nRow][4];
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < 4; j++) {
				seats[i][j] = new Seat(i,j,false);
			}
		}
		int numOf80 = (int)(Math.random() + nRow * 4 * 0.8); // Car의 seat개수의 80%에 해당하는 수를 구함
		int sum = 0;
		while (sum != numOf80) {
			int randRow = (int)(Math.random() * nRow);
			int randCol = (int)(Math.random() * 4);
			if (seats[randRow][randCol].reserved == false) {
				seats[randRow][randCol].reserved = true;
				sum += 1;
			}
		}
	}
	
	int avSeatEx() {
		int sum = 0;
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < 4; j++) {
				if (seats[i][j].reserved == false)
					sum += 1;
			}
		}
		return sum;
	}
	int avSeatStd() {
		int sum = 0;
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < 4; j++) {
				if (seats[i][j].reserved == false)
					sum += 1;
			}
		}
		return sum;
	}
}

class Seat{
	int row;
	int col;
	boolean reserved;
	
	Seat(){}
	Seat(int row, int col, boolean reserved){
		this.row = row;
		this.col = col;
		this.reserved = reserved;
	}
	
	boolean isWindow() {
		if (col == 0 || col == 3)
			return true;
		else
			return false;
	}
}

