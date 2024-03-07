import random

class Player:
    def __init__(self, name):
        self.name = name
        self.score = 0
        self.turn_total = 0

    def roll_die(self):
        roll = random.randint(1, 6)
        if roll == 1:
            self.turn_total = 0
            print(f"{self.name} rolled a 1! Turn total: 0")
            return 1
        else:
            self.turn_total += roll
            print(f"{self.name} rolled a {roll}. Turn total: {self.turn_total}")
            return 0

    def hold(self):
        self.score += self.turn_total
        self.turn_total = 0
        print(f"{self.name} holds. Score: {self.score}")
        return self.score

class Die:
    @staticmethod
    def roll():
        return random.randint(1, 6)

class Game:
    def __init__(self, player1, player2):
        self.players = [player1, player2]
        self.current_player_index = 0

    def play_turn(self):
        player = self.players[self.current_player_index]
        choice = input(f"{player.name}, roll (r) or hold (h)? ").lower()
        if choice == 'r':
            result = player.roll_die()
            if result == 1:
                self.current_player_index = (self.current_player_index + 1) % 2
        elif choice == 'h':
            player.hold()
            self.current_player_index = (self.current_player_index + 1) % 2
        else:
            print("Invalid choice. Please enter 'r' to roll or 'h' to hold.")

    def check_win(self):
        for player in self.players:
            if player.score >= 100:
                return True
        return False

    def display_scores(self):
        for player in self.players:
            print(f"{player.name}'s score: {player.score}")

    def play_game(self):
        random.seed(0)
        while not self.check_win():
            print("\nNew Turn")
            self.play_turn()
            self.display_scores()
        print(f"\n{self.players[self.current_player_index].name} wins!")

if __name__ == "__main__":
    player1 = Player("Player 1")
    player2 = Player("Player 2")
    game = Game(player1, player2)
    game.play_game()
