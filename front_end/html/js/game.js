let userScore = 0;
let computerScore = 0;

const choices = document.querySelectorAll(".choice");
const msg = document.querySelector("#msg");

const userScorePara = document.querySelector("#user-score");
const computerScorePara = document.querySelector("#computer-score");

const genComputerChoice = () => {
  const options = ["rock", "paper", "scissors"];

  const ranIdx = Math.floor(Math.random() * 3);
  return options[ranIdx];
};

const showWinner = (userWin) => {
  if (userWin) {
    userScore++;
    userScorePara.innerText = userScore;
    console.log("You Win");
    msg.innerText = "You Win";
  } else {
    computerScore++;
    computerScorePara.innerText = computerScore;
    console.log("you Lose");
    msg.innerText = "You Loss";
  }
};

const drawGame = () => {
  console.log("Game Was Draw");
  msg.innerText = "Draw match";
};

const playGame = (userChoice) => {
  const compChoice = genComputerChoice();
  console.log("computer Choice:::::::::" + compChoice);
  console.log("User Choice:::::::::::::" + userChoice);
  if (compChoice === userChoice) {
    drawGame();
  } else {
    let userWin = true;
    if (userChoice === "rock") {
      userWin = compChoice === "paper" ? false : true;
    } else if (userChoice === "paper") {
      userWin = compChoice === "scissors" ? false : true;
    } else {
      userWin = compChoice === "rock" ? false : true;
    }
    showWinner(userWin);
  }
};

choices.forEach((choices) => {
  choices.addEventListener("click", () => {
    const userChoice = choices.getAttribute("id");
    playGame(userChoice);
  });
});
