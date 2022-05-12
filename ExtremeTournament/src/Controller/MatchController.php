<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MatchController extends AbstractController
{
    /**
     * @Route("/match", name="app_match")
     */
    public function index(): Response
    {
        return $this->render('match/index.html.twig', [
            'controller_name' => 'MatchController',
        ]);
    }


    /**
     * @Route("/matchback", name="app_matchback")
     */
    public function indexback(): Response
    {
        return $this->render('match/matchback.html.twig', [
            'controller_name' => 'MatchController',
        ]);
    }
}
